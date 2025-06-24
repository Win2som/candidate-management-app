import { Component, OnInit } from '@angular/core';
import {Candidate} from "../candidate.model";
import {CandidateService} from "../candidate.service";

@Component({
  selector: 'app-candidate',
  templateUrl: './candidate.component.html',
  styleUrls: ['./candidate.component.css']
})
export class CandidateComponent implements OnInit {
  candidates: Candidate[] = [];
  newCandidate: Candidate = {id: 0, name: '', email: '', position: '' };
  page = 0;
  size = 5;
  totalPages = 0;
  showModal = false;
  successMessage: string = '';
  errorMessage: string = '';

  constructor(private candidateService: CandidateService) {}

  ngOnInit() {
    this.getCandidates();
  }

  getCandidates() {
    this.candidateService.getCandidates(this.page, this.size).subscribe(data => {
      this.candidates = data.content;
      this.totalPages = data.totalPages;
    },
      (error) => {
        alert("An error occurred: " + (error.error || "Unknown error"));
      });
  }


  addCandidate() {
    this.candidateService.addCandidate(this.newCandidate).subscribe(() => {
      this.successMessage = 'Candidate added successfully!';
      this.errorMessage = '';
      this.getCandidates();
      this.resetForm();
      this.closeModal();
      this.autoCloseAlert();
      },
      (error) => {
      this.errorMessage = "An error occurred: " + (error.error || "Unknown error");
      this.successMessage = '';
      this.autoCloseAlert();
      this.resetForm();
      this.closeModal();
    });
  }

  deleteCandidate(id: number) {
    if(confirm("Are you sure you want to delete this candidate?")) {
      this.candidateService.deleteCandidate(id).subscribe({
        next: (response) => {
          this.getCandidates();
          this.successMessage = response?.message || "Candidate deleted successfully!";
          this.errorMessage = '';
          this.autoCloseAlert();
        },
        error: (error) => {
          this.errorMessage = "Error deleting candidate: " + (error.error || "Unknown error");
          this.successMessage = '';
          this.autoCloseAlert();
        }
      });
    }
  }

  prevPage() {
    if(this.page > 0) {
      this.page--;
      this.getCandidates();
    }
  }

  nextPage() {
    if(this.page < this.totalPages - 1) {
      this.page++;
      this.getCandidates();
    }
  }

  openModal() {
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
    this.resetForm();
  }

  resetForm() {
    this.newCandidate = { id: 0, name: '', email: '', position: '' };
  }

  closeAlert() {
    this.successMessage = '';
    this.errorMessage = '';
  }

  autoCloseAlert() {
    setTimeout(() => {
      this.closeAlert();
    }, 5000);
  }
}
